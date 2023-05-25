import Vue from "vue";
import VueRouter from "vue-router";
import SignupView from "@/views/SignupView.vue";
import SigninView from "@/views/SigninView.vue";
import PlanTripView from "@/views/PlanTripView.vue";
import PlanListView from "@/views/PlanListView.vue";
import PlanDetailView from "@/views/PlanDetailView.vue";
// import AttractionDetailView from "@/views/AttractionDetailView.vue";
import AttractionListView from "@/views/AttractionListView.vue";
import MyPageView from "@/views/MyPageView.vue";
import MainView from "@/views/MainView.vue";
import WishList from '@/views/MyWishList';
import UserModify from '@/views/UserModify';
import RandomTripDetailView from '@/views/RandomTripDetailView'
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/attractionDetail",
    name: "attractionDetail",
    // component: AttractionDetailView
    component: () => import(/* webpackChunkName: "board" */ "@/views/AttractionDetailView.vue"),
  },
  {
    path: "/attractionList",
    name: "attractionList",
    component: AttractionListView,
  },
  {
    path: "/RandomTripDetailView",
    name: "RandomTripDetailView",
    component: RandomTripDetailView,
  },
  {
    path: "/myPage",
    name: "myPage",
    component: MyPageView,
  },
  {
    path: "/planDetail",
    name: "planDetail",
    component: PlanDetailView,
  },
  {
    path: "/planList",
    name: "planList",
    component: PlanListView,
  },
  {
    path: "/planTrip/:lat/:lng",
    name: "planTrip",
    component: PlanTripView,
  },
  {
    path: "/signin",
    name: "signin",
    component: SigninView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
  },
  {
    path: "/wishLst",
    name: "wishList",
    component: WishList,
  },
  {
    path: "/UserModify",
    name: "UserModify",
    component: UserModify,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
