import { tripInstance } from "@/api/http.js";

const api = tripInstance();

function sidoList(success, fail) {
  api.get(`/attract/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/attract/gugun`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList };