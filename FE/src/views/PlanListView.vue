<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <h3>여행 일정 -</h3>
        &nbsp; &nbsp;
        <h3 style="color: #0a97cd">{{ planLength }} 건</h3>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>
    <br />
    <b-container class="bv-example-row">
      <template>
        <b-row style="margin-top: 20px" v-for="planData in UserPlanList" :key="planData.plan_id">
          <b-col cols="12" class="center">
            <b-card no-body class="overflow-hidden" style="width: 100%; height: 200px">
              <b-row no-gutters>
                <b-col md="3" @click="goPlanDetail(planData.plan_id)">
                  <b-card-img
                    :src="planData.first_image"
                    alt="Image"
                    class="rounded-0"
                    style="height: 100%"
                  ></b-card-img>
                  <div class="d-day-circle">{{ planData.dday }}</div>
                </b-col>
                <b-col md="9">
                  <b-card-body>
                    <b-card-text>
                      <b-row style="margin: 0px">
                        <b-col cols="4">
                          <div>
                            <div class="travel-title">
                              {{ planData.sidoName }}
                            </div>
                            <div class="uk-text-meta">
                              {{ planData.sidoName }}, {{ planData.gugunName }}
                            </div>
                            <!-- <div
                              class="uk-text-meta"
                              style="font-size: 12px; margin-top: 8px"
                            >
                              plan 주인
                            </div> -->
                            <div class="info-container" style="margin-top: 40px"></div>
                          </div>
                        </b-col>
                        <b-col cols="8">
                          <div style="height: 50%">
                            <div class="small-title" style="float: left; width: 60%">
                              여행이름
                              <span class="content-text" style="line-height: 40px">
                                {{ planData.plan_name }}
                              </span>
                            </div>

                            <div class="small-title" style="float: right; width: 40%">
                              최종인원

                              <span class="content-text" style="line-height: 40px">
                                {{ planData.userCount }}인
                              </span>
                            </div>
                          </div>
                          <div style="height: 50%">
                            <div class="small-title" style="float: left; width: 60%">
                              여행 일자
                              <span class="content-text" style="line-height: 40px">
                                {{ planData.plan_start }} ~
                                {{ planData.plan_end }}
                              </span>
                            </div>

                            <div class="small-title" style="float: right; width: 40%">
                              선택장소

                              <span class="content-text" style="line-height: 40px">
                                {{ planData.stopCount }}
                              </span>
                            </div>
                          </div>
                        </b-col>
                      </b-row>
                      <b-row style="margin: 0px">
                        <b-col>
                          <div>
                            <button class="plan-button" @click="goPlanDetail(planData.plan_id)">
                              일정 상세
                            </button>
                            <button
                              class="plan-button"
                              v-b-modal.modal-prevent-closing
                              @click="sharePlan(planData.plan_id)"
                            >
                              초대 하기
                            </button>
                            <button
                              class="plan-button"
                              @click="deletePlan(planData.plan_id, planData.userId)"
                            >
                              일정 삭제
                            </button>
                          </div>
                        </b-col>
                      </b-row>
                    </b-card-text>
                  </b-card-body>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
      </template>
    </b-container>

    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="여행 일정 초대하기"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <h6 style="font-weight: 900">
          여행 일정에 초대할 사용자의 계정 또는 닉네임을 입력해주세요
        </h6>
        <br />
        <b-form-group
          label="Account or NickName"
          label-for="name-input"
          invalid-feedback="Account or NickName is required"
          :state="nameState"
        >
          <b-form-input id="name-input" v-model="name" :state="nameState" required></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import { userPlan } from "@/api/plan";
import { mapState } from "vuex";
import { joinPlanMember, deletePlan_User } from "@/api/plan";

const memberStore = "memberStore";

export default {
  components: {},
  data() {
    return {
      planLength: 0,
      UserPlanList: null,
      mainImg: "http://tong.visitkorea.or.kr/cms/resource/25/2823725_image2_1.jpg",
      name: "",
      nameState: null,
      tmp_planId: null,
    };
  },
  created() {
    var userId = this.userInfo.userId;
    var this_temp = this;
    userPlan(
      userId,
      ({ data }) => {
        console.log(data);
        this_temp.planLength = data.planLength;
        this_temp.UserPlanList = data.UserPlanList;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  mounted() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    UserPlanList() {
      console.log("여긴 watch");
      console.log(this.UserPlanList);
    },
  },
  methods: {
    goPlanDetail(planId) {
      // alert(planId);
      this.$router.push({ name: "planDetail", query: { planId: planId } });
    },
    sharePlan(planId) {
      this.tmp_planId = planId;
      // alert("일정 공유 구현");
    },
    deletePlan(planId, ownerUserId) {
      var userId = this.userInfo.userId;
      console.log(planId, ownerUserId, userId);

      var this_temp = this;

      deletePlan_User(
        planId,
        ownerUserId,
        userId,
        ({ data }) => {
          console.log(data);
          if (data == "plan delete success") {
            alert("여행 삭제가 완료되었습니다.");
          }
          if (data == "success") {
            alert("여행 일정에서 사용자가 삭제되었습니다.");
          }
          userPlan(
            userId,
            ({ data }) => {
              console.log(data);
              this_temp.planLength = data.planLength;
              this_temp.UserPlanList = data.UserPlanList;
            },
            (error) => {
              console.log(error);
            }
          );
        },
        (error) => {
          console.log(error);
        }
      );
    },
    handleOk(bvModalEvent) {
      bvModalEvent.preventDefault();
      this.handleSubmit();
    },
    handleSubmit() {
      if (!this.checkFormValidity()) {
        return;
      }
      console.log(this.name);

      var userAccount = this.name;
      var userId = this.userInfo.userId;
      var this_temp = this;

      joinPlanMember(
        this.tmp_planId,
        userAccount,
        ({ data }) => {
          console.log(data);
          if (data == "not user") {
            alert("등록된 계정이 없습니다. 다시 확인해주세요");
            this.resetModal();
          }
          if (data == "already") {
            alert("이미 추가된 사용자입니다.");
            this.resetModal();
            this.$nextTick(() => {
              this.$bvModal.hide("modal-prevent-closing");
            });
          }
          if (data == "success") {
            alert("여행 일정에 사용자가 추가되었습니다.");
            this.$nextTick(() => {
              this.$bvModal.hide("modal-prevent-closing");
              this.tmp_planId = null;
              userPlan(
                userId,
                ({ data }) => {
                  console.log(data);
                  this_temp.planLength = data.planLength;
                  this_temp.UserPlanList = data.UserPlanList;
                },
                (error) => {
                  console.log(error);
                }
              );
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid;
      return valid;
    },
    resetModal() {
      this.name = "";
      this.nameState = null;
    },
  },
};
</script>

<style scoped>
.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
h3 {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial,
    "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol",
    "Noto Color Emoji";
  font-weight: 600;
  color: #333;
  text-transform: none;
}

.d-day-circle {
  font-family: "Montserrat";
  position: absolute;
  top: 8px;
  left: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #26dbe1;
  color: #fff;
  font-weight: 700;
  font-size: 0.8rem;
}

element.style {
  font-size: 12px;
  margin-top: 8px;
}
.travel-title {
  font-family: "Montserrat" !important;
  font-size: 1.6rem;
  font-weight: 700;
  color: #000;
}

.uk-text-meta {
  font-size: 0.875rem;
  line-height: 1.4;
  color: #999;
}
.small-title {
  font-size: 1rem;
  font-weight: 700;
  color: #5dc9dd;
}

.small-text {
  font-size: 12px;
  /* text-align: center; */
  color: #616161;
  letter-spacing: 1px;
  padding: 8px;
}

.content-text {
  font-size: 0.9rem;
  color: #000;
  font-family: "Montserrat";
}

#test {
  font-weight: normal;
  color: black;
  transition: all 0.3s;
  box-shadow: none;
}

/* .clickedOrder {
  font-weight: bold;
  color: blue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
} */

b-col span {
  font-weight: bold;
  font-size: 30px;
}
.test {
  font-weight: bold;
  font-size: 30px;
  content: "";
  height: 5px;
  width: 100%;
  transition: 0.3s;
  position: absolute;
  bottom: 0;
  left: 0;
  background-color: rgb(211, 120, 120);
}

text {
  font-weight: bold;
  font-size: 30px;
}

.clickedOrder {
  font-weight: bold;
  display: flex;
  justify-content: center;
  text-decoration-line: underline;
  text-decoration-color: rgb(231, 125, 125);
  text-decoration-thickness: 3px;
}

.tit a {
  overflow: hidden;
  display: inline-block;
  width: 100%;
  font-weight: bold;
  font-size: 20px;
  color: #000;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.boader {
  width: 100px;
  height: 100px;
  background-color: #f5d682;
  border: 1px solid rgba(71, 68, 68, 0.048);
}

.total_check strong span {
  padding: 0 2px;
  color: #0a97cd;
}

.btn {
  font-size: 1em;
  font-family: NotoSansKR, NotoSansJP, "돋움", Dotum, AppleGothic, Sans-serif;
  padding: 3px 3px;
  height: auto;
  border-top-left-radius: 35px;
  border-top-right-radius: 35px;
  border-bottom-left-radius: 35px;
  border-bottom-right-radius: 35px;
  background-color: #aab6c9;
  color: #fff;
  font-size: 14px;
  letter-spacing: -0.6px;
}

button {
  background-color: transparent;
  border-radius: 20px;
  padding: 7px;
  border-color: #e2e2e2;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9, 7px 7px 20px 0px #0002,
    4px 4px 5px 0px #0001;
}

.plan-button {
  margin: 0 10px;
  border: none;
  overflow: visible;
  font: inherit;
  color: inherit;
  text-transform: none;
  -webkit-appearance: none;
  border-radius: 0;
  display: inline-block;
  box-sizing: border-box;
  padding: 0 30px;
  vertical-align: middle;
  font-size: 0.875rem;
  line-height: 38px;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.1s ease-in-out;
  transition-property: color, background-color, border-color;
  line-height: 53px;
  font-size: 0.875rem;
  background: #fff;
  color: #666;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}
</style>
