<template>
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="changeGugun"></b-form-select>
    <b-form-input></b-form-input>
    <b-input-group-append>
      <b-button variant="info">Button</b-button>
    </b-input-group-append>
    </b-col>
  </template>
  
  <script>
  import { mapState, mapActions, mapMutations } from "vuex";
  
  /*
    namespaced: true를 사용했기 때문에 선언해줍니다.
    index.js 에서 modules 객체의 '키' 이름입니다.
  
    modules: {
      키: 값
      memberStore: memberStore,
      houseStore: houseStore
    }
  */
  const itemStore = "itemStore";
  
  export default {
    name: "ChooseGugun",
    data() {
      return {
        gugunCode: null,
      };
    },
    props: {
      sidoCode:null,
    },
    watch: {
        sidoCode() {
        console.log("우히히히");
        this.CLEAR_GUGUN_LIST();
        this.gugunCode = null;
        console.log(this.sidoCode);
        if (this.sidoCode) this.getGugun(this.sidoCode);
      },
    },
    computed: {
      ...mapState(itemStore, ["guguns"]),
    },
    created() {},
    methods: {
      ...mapActions(itemStore, ["getGugun"]),
      ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),
      changeGugun() {
        this.$emit("select-gugun", this.gugunCode);
      },
    },
  };
  </script>
  
  <style scoped></style>
  