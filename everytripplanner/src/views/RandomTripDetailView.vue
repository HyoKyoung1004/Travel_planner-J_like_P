<template>
    <div >
        <div class="parent">
        <b-modal id="modal-1" title="BootstrapVue" v-model="showModal" @ok="myData()" @cancel="gotoMain" hide-header-close cancel-title="메인" ok-title="작성" no-close-on-esc
              no-close-on-backdrop>
            <label for="example-datepicker">당일치기 날짜</label>
            <b-form-datepicker id="start_date" v-model="startDate" class="mb-2"></b-form-datepicker>
             <b-form-input  v-model="planName" placeholder="플랜명을 기입해주세요"></b-form-input>
        </b-modal>
          <random-gugun @momsido="momsido" @sidonum="sidonum"></random-gugun>
          <random-sido :data="data" :data2="data2"></random-sido>
      </div>



<div style="display: flex; justify-content: center;" v-if="changeObject">
  <ul style="list-style-type: none; padding: 0; display: flex; flex-direction: row;">
    <li v-for="(value, key) in changeObject" :key="key" style="flex: 1;">
      <b-card
        v-if="key !== 0"
        :title="value.title"
        :img-src="value.firstImage"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 17rem; flex: 1; height: 100%;" 
        class="mb-2"
      >
      </b-card>
    </li>
  </ul>
  <b-button @click="shoot(changeObject)">이걸로 여행 채택~~</b-button>
      <b-nav class="child">
        <b-button variant="success"  active @click="viewAttractionList(12)" >관광지 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(14)" >문화시설 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(15)" >축제공연행사 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(28)" >레포츠 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(32)" >숙박 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(38)" >쇼핑 위주</b-button>
        <b-button variant="success"  active @click="viewAttractionList(39)" >음식점 위주</b-button>
      </b-nav>
</div>


    </div>
</template>
<script>
import RandomGugun from "@/components/Plan/RandomGugun.vue";
import RandomSido from "@/components/Plan/RandomSido.vue";
import {mapActions, mapState} from "vuex";
import axios from "axios";
const planStore = "planStore";
const memberStore = "memberStore";
export default({
  computed:{
    ...mapState(planStore,["randomplan","sido","gugun"]),
    ...mapState(memberStore,["userInfo"])
  },
  watch: {
    randomplan(){
        console.log("변했당");
        if( this.randomplan==null ||this.randomplan==undefined || this.randomplan.length==0 ){
          alert("그 지역에는 아무런 여행지가 없답니다 꽝!!!!");
          this.changeObject=null;
          return;
        }
        this.changeObject = this.randomplan;
    }
  },
  components:{
    RandomSido,
    RandomGugun
  },
    mounted(){
         this.showModal=true;
    },
    data() {
        return {
            startDate: "",
            endDate:this.startDate,
            showModal:true,
            planName:"",
            data:null,
            data2:0,
            changeObject:null,
            showFirstRoulette: true,
            showSecondRoulette: false,
            tmpResponse:null
        };
    },
    methods:{
      
      viewAttractionList(idx) {   
            const response = axios.get(`http://localhost:9999/trip/attract/random/${this.sido}/${this.gugun}/${idx}`).then((response)=> 
            {this.tmpResponse = response.data;
              console.log(this.tmpResponse)});
            this.setRandomPlan(response.data);
          },
      gotoMain(){
        this.$router.push({ name: "main" });
      },
      ...mapActions(planStore,["setRandomPlan"]),
      sidonum(data2){
        this.data2=data2;
      },
      momsido(data){
        this.data=data;
        console.log(this.data);
      }
      ,
      myData(){
        if(this.startDate=="" || this.endDate=="" || this.planName==""){
                if(this.planName==""){
                    alert("플랜명을 제대로 기입해주세요");
                }
                if(this.startDate=="" && this.endDate==""){
                    alert("날짜를 모두다 기입해주세요");
                }
                else if(this.startDate==""){
                alert("출발할 날짜를 기입해주세요");
                
                }
                else if(this.endDate==""){
                    alert("도착할 날짜를 기입해주세요");   
                }
                this.$router.go(0);
            }
      },
      shoot(randomplan){
        const randommyplan = [randomplan];
        console.log(this.startDate)
        const shootPlan ={
          startDate:this.startDate,
          endDate:this.startDate,
          userId:this.userInfo.userId,
          planlist: randommyplan.map((items) =>
              items.map((item, subIndex) => {
            if (subIndex === 0 && item.__ob__) {
            // 첫 번째 요소가 옵저버 객체인 경우, 넘어감
            return null;
            }
            return {
            content_id: item.contentId,
            num: subIndex + 1
            };
        }).filter(Boolean)
        )
        ,
        plan_name:this.planName
        }
        console.log(shootPlan);
        axios.post("http://localhost:9999/trip/plan/realPlanAdd",shootPlan);
        this.$router.push({ name: "planList" });
        
      }
    }
})  
</script>

<style scoped>
.parent {
  display: flex;
  justify-content: center;
}
</style>