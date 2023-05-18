import { tripInstance } from "./http.js";

const api = apiInstance();

function attractdionDetail(contentId, success, fail) {
    api.get(`/attraction/view/${contentId}`, ).then(success).catch(fail);
}

function attractdionList(searchData, page, orderType, success, fail) {
    api.get(`/attract/search/${searchData}/${page}/${orderType}`, ).then(success).catch(fail);
  }
  
function attractdionList(sido, gugun, page, orderType, success, fail) {
    api.get(`/attract/search/${sido}/${gugun}/${page}/${orderType}`, ).then(success).catch(fail);
}
  
function attractdionList(sido, gugun,searchData, page, orderType, success, fail) {
    api.get(`/attract/search/${sido}/${gugun}/${searchData}/${page}/${orderType}`, ).then(success).catch(fail);
}
  
function attractdionList(searchData,type, page, orderType, success, fail) {
    api.get(`/attract/searchType/${searchData}/${type}/${page}/${orderType}`, ).then(success).catch(fail);
}

function attractdionList(searchData,type, page, orderType, success, fail) {
    api.get(`/attract/searchType/${sido}/${gugun}/${searchData}/${type}/${page}/${orderType}`, ).then(success).catch(fail);
}

function attractdionList(sido, gugun,searchData,type, page, orderType, success, fail) {
    api.get(`/attract/searchType/${sido}/${gugun}/${searchData}/${type}/${page}/${orderType}`, ).then(success).catch(fail);
}

function attractdionList(sido, gugun,type, page, orderType, success, fail) {
    api.get(`/attract/searchType/${sido}/${gugun}/${type}/${page}/${orderType}`, ).then(success).catch(fail);
}

function attractdionList(type, page, orderType, success, fail) {
    api.get(`/attract/searchType/${type}/${page}/${orderType}`, ).then(success).catch(fail);
}

function route(success, fail) { 
    api.get(`/attract/attraction/route`, ).then(success).catch(fail);
}