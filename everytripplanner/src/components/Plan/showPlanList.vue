<template>
    <div id="asdasd">
        <b-button @click="shootPlan">이제 진짜 보내보자</b-button>
    <b-button v-b-modal.modal-1>달력 정하기</b-button>
        <b-modal id="modal-1" title="BootstrapVue" @ok="myData">
            <label for="example-datepicker">시작 날짜</label>
            <b-form-datepicker id="start_date" v-model="startDate" class="mb-2"></b-form-datepicker>
            <label for="example-datepicker">끝날짜</label>
            <b-form-datepicker id="end_dater" v-model="endDate" class="mb-2"></b-form-datepicker>
        </b-modal>
        <b-dropdown v-if="dayDifference" text="일자 선택" variant="primary">
            <b-dropdown-item v-for="(value, key) in dayDifference" :key="key" @click="searchDay(value)">
            {{ value }}일
        </b-dropdown-item>
    </b-dropdown>
                <attraction-add  v-if="selectedDay" :day="selectedDay" />
        </div>
</template>

<script>
import AttractionAdd from "@/components/Plan/items/AttractionAdd.vue"
import { mapState, mapMutations } from "vuex";
import axios from 'axios';
const planStore = "planStore";
export default {
    props:{
        card:[],
    },
    components: {
        AttractionAdd
    },
    data() {
        return {
            message: '',
            startDate: "",
            endDate:"",
            dayDifference:0,
            selectedDay: null,
        };
    },
    created() {},
    methods: {
        ...mapMutations(planStore,["setPlannerItem",'setSelectedDay','setStartDate','setEndDate']),
        myData() {
            console.log("1111")
            const oldDate = new Date(this.startDate);
            const newDate = new Date(this.endDate);
            this.setStartDate(this.startDate);
            this.setEndDate(this.endDate);
            let diff = Math.abs(newDate.getTime() - oldDate.getTime());
            diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
            this.dayDifference=diff;
              for (let i = 1; i <= this.dayDifference; i++) {
                const key = i;
                this.setPlannerItem( {key, value : {} });
            }
        },
        searchDay(day){
            console.log(day);
            if (this.$store.state.planItems && this.$store.state.planItems[day]) {
                const selectedPlan = this.$store.state.planItems[day];
                console.log("Selected Plan:", selectedPlan);
                }
            this.selectedDay=day;
            this.setSelectedDay(day);
            },
        shootPlan(){
            const shootMyPlan={
                startDate:this.startDate,
                endDate:this.endDate,
                userId:1,
                planlist: this.planItems.map((items) =>
                            items.map((item) => ({ content_id: item.content_id, num:1}))),
                plan_name:"myFan"
            }
            console.log("shoooooooot",shootMyPlan);
            axios.post("http://localhost:9999/trip/plan/realPlanAdd",shootMyPlan);
        },  
        },
    computed: {
        ...mapState(planStore, ["planItems",'selectedDayNum']),
    }
};
</script>

<style scoped></style>