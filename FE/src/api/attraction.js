import { tripInstance } from "./http.js";

const api = tripInstance();

function attractionDetail(contentId, success, fail) {
  api.get(`/attract/view/${contentId}`).then(success).catch(fail);
}
// function attractdionList_searchData(searchData, page, orderType, success, fail) {
//   api.get(`/attract/search/${searchData}/${page}/${orderType}`).then(success).catch(fail);
// }

function attractdionList_addr(sido, gugun, page, orderType, success, fail) {
  console.log("시도")
  console.log(sido, gugun, page, orderType);
  api.get(`/attract/search/${sido}/${gugun}/${page}/${orderType}`).then(success).catch(fail);
}

// function attractdionList_addr_searchData(sido, gugun, searchData, page, orderType, success, fail) {
//   api
//     .get(`/attract/search/${sido}/${gugun}/${searchData}/${page}/${orderType}`)
//     .then(success)
//     .catch(fail);
// }

// function attractdionList_searchData_type(searchData, type, page, orderType, success, fail) {
//   api
//     .get(`/attract/searchType/${searchData}/${type}/${page}/${orderType}`)
//     .then(success)
//     .catch(fail);
// }

function attractdionList_addr_type(sido, gugun, type, page, orderType, success, fail) {
  console.log("시도 타입")
  console.log(sido, gugun, type, page, orderType);
  api
    .get(`/attract/searchType/${sido}/${gugun}/${type}/${page}/${orderType}`)
    .then(success)
    .catch(fail);
}

function attractdionList_addr_searchData_type(
  sido,
  gugun,
  searchData,
  type,
  page,
  orderType,
  success,
  fail
) {

  api
    .get(`/attract/search/${sido}/${gugun}/${searchData}/${type}/${page}/${orderType}`)
    .then(success)
    .catch(fail);
}

function attractionList_type(type, page, orderType, success, fail) {
  console.log("타입만")
  console.log(type, page, orderType);
  api.get(`/attract/searchType/${type}/${page}/${orderType}`).then(success).catch(fail);
}

function route(success, fail) {
  api.get(`/attract/attraction/route`).then(success).catch(fail);
}

function likeTop4(success, fail) {
  api.get(`/attract/view/likeTop4`).then(success).catch(fail);
}

export {
  attractionDetail,
  attractionList_type,
  attractdionList_addr_searchData_type,
  route,
  likeTop4,
  attractdionList_addr,
  attractdionList_addr_type,
};
