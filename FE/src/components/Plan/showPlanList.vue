<template>
  <div id="asdasd">
    <b-button @click="shootPlan">이제 진짜 보내보자</b-button>
    <b-modal
      id="modal-1"
      title="BootstrapVue"
      v-model="showModal"
      @ok="myData()"
      hide-header-close
      @cancel="gotoMain"
      cancel-title="메인"
      ok-title="작성"
      no-close-on-esc
      no-close-on-backdrop
    >
      <label for="example-datepicker">시작 날짜</label>
      <b-form-datepicker id="start_date" v-model="startDate" class="mb-2"></b-form-datepicker>
      <label for="example-datepicker">끝날짜</label>
      <b-form-datepicker id="end_dater" v-model="endDate" class="mb-2"></b-form-datepicker>
      <b-form-input v-model="planName" placeholder="플랜명을 기입해주세요"></b-form-input>
    </b-modal>
    <b-dropdown v-if="dayDifference" :text="selectedDayText" variant="primary">
      <b-dropdown-item v-for="(value, key) in dayDifference" :key="key" @click="searchDay(value)">
        {{ value }}일
      </b-dropdown-item>
    </b-dropdown>
    <attraction-add v-if="selectedDay" :day="selectedDay" />
  </div>
</template>

<script>
import AttractionAdd from "@/components/Plan/items/AttractionAdd.vue";
import { mapState, mapMutations } from "vuex";
import axios from "axios";
const planStore = "planStore";
const memberStore = "memberStore";
export default {
  props: {
    card: [],
  },
  components: {
    AttractionAdd,
  },
  mounted() {
    this.showModal = true;
  },
  data() {
    return {
      message: "",
      startDate: "",
      endDate: "",
      dayDifference: 0,
      selectedDay: null,
      showModal: true,
      planName: "",
      selectedDayText: "날짜 선택",
    };
  },
  created() {},
  methods: {
    ...mapMutations(planStore, ["setPlannerItem", "setSelectedDay", "setStartDate", "setEndDate"]),
    gotoMain() {
      this.$router.push({ name: "main" });
    },
    myData() {
      console.log(typeof this.startDate);
      console.log(this.planName);
      if (this.startDate == "" || this.endDate == "" || this.planName == "") {
        if (this.planName == "") {
          alert("플랜명을 제대로 기입해주세요");
        }
        if (this.startDate == "" && this.endDate == "") {
          alert("날짜를 모두다 기입해주세요");
        } else if (this.startDate == "") {
          alert("출발할 날짜를 기입해주세요");
        } else if (this.endDate == "") {
          alert("도착할 날짜를 기입해주세요");
        }
        this.$router.go(0);
      } else {
        const oldDate = new Date(this.startDate);
        const newDate = new Date(this.endDate);

        if (oldDate > newDate) {
          alert("출발하는 날짜와 도착하는 날짜가 서로 바뀐것 같습니다");
        }
        this.planNameAdd = this.planName;
        console.log(this.planNameAdd);
        this.setStartDate(this.startDate);
        this.setEndDate(this.endDate);
        let diff = Math.abs(newDate.getTime() - oldDate.getTime());
        diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
        this.dayDifference = diff + 1;
        console.log(this.dayDifference);

        for (let i = 0; i < this.dayDifference; i++) {
          const key = i;
          this.dayNum[i] = 1;
          console.log("!!!!!!!!!!!!!!!!", this.dayNum);
          this.setPlannerItem({ key, value: {} });
        }
        this.showModal = false;
      }
    },
    searchDay(day) {
      console.log(day);
      if (this.$store.state.planItems && this.$store.state.planItems[day]) {
        const selectedPlan = this.$store.state.planItems[day];
        console.log("Selected Plan:", selectedPlan);
      }
      this.selectedDay = day;
      this.selectedDayText = `${day}일`;
      this.setSelectedDay(day);
    },
    shootPlan() {
      console.log("GNGNGNGNGNGNGNGNG", this.dayNum);
      console.log(this.userInfo);
      console.log(this.planNameAdd);
      const shootMyPlan = {
        startDate: this.startDate,
        endDate: this.endDate,
        userId: this.userInfo.userId,
        planlist: this.planItems.map((items) =>
          items
            .map((item, subIndex) => {
              if (subIndex === 0 && item.__ob__) {
                // 첫 번째 요소가 옵저버 객체인 경우, 넘어감
                return null;
              }
              return {
                content_id: item.content_id,
                num: subIndex + 1,
              };
            })
            .filter(Boolean)
        ),
        plan_name: this.planName,
      };
      console.log("shoooooooot", shootMyPlan);
      axios.post("http://localhost:9999/trip/plan/realPlanAdd", shootMyPlan).then((resp) => {
        console.log(resp);
        this.$router.push({ name: "planList" });
        this.selectedDayNum = null;
      });
    },
  },
  computed: {
    ...mapState(planStore, [
      "planItems",
      "selectedDayNum",
      "dayNum",
      "planNameAdd",
      "selectedDayNum",
    ]),
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped></style>
