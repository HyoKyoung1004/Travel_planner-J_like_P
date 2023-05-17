<template>
  <div>
      <tr>
      <v for="item in items">
          <td v-on:click="legend">{{item.sido_name}}</td>
      </v>
          <choose-gu-gun
            v-for="item in items"
            v-bind:ii="gugun" v-bind:key="item.gugun">
          </choose-gu-gun>
    </tr>
  </div>
</template>

<script>
import axios from "axios"
import ChooseGuGun from "./ChooseGuGun.vue";
export default {
  components: {
    ChooseGuGun
  },
  data() {
    return {
      items: [],
      gugun:{},
    };
  },
  created() {
    axios.get("http://192.168.214.40:9999/trip/attract/").then((resp)=>{
            console.log(resp);
            this.items=resp.data
        })
  },
  methods: {
    legend() {
      axios.get("http://192.168.214.40:9999/vuews/book"+this.items.sido_name).then((resp)=>{
            console.log(resp);
            this.gugun=resp.data
        })
    }
  },
};
</script>

<style scoped></style>