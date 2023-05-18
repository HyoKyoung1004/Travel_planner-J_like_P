<template>
        <div class="row">
                <b-input-group class="mt-3">
                    <b-form-input readonly></b-form-input>
                    <b-input-group-append>
                    <b-button v-model="sideCode" @change="changeSido" variant="outline-success" v-b-modal.modal-1>시도</b-button>
                    </b-input-group-append>
                </b-input-group>
        <choose-gu-gun></choose-gu-gun>
        </div>
        
</template>

<script>
import axios from "axios"
import ChooseGuGun from "./ChooseGuGun.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const itemStore = "itemStore";
export default {
  name:"SelectSido",
  components: {
    ChooseGuGun
  },
  data() {
    return {
      items: [],
      sideCode:null,
    };
  },
  computed: {
    ...mapState(itemStore,["sidos"]),
  },
  created() {
    axios.get("http://192.168.214.40:9999/trip/attract/").then((resp) => {
      console.log(resp);
      this.items = resp.data
    }),
    this.CLEAR_SIDO_LIST();
    this.getSid();
  },
  methods: {
    ...mapActions(itemStore, ["getSido"]),
    ...mapMutations(itemStore, ["CLEAR_SIDO_LIST"]),
    changeSido() {
      // console.log("시도 선택 ::: " + this.sidoCode);
      this.$emit("select-sido", this.sidoCode);
    },
  },
};
</script>

<style scoped></style>