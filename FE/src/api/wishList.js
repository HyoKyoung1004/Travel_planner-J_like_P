import { tripInstance } from "./http.js";

const api = tripInstance();

//유저의 위시리스트에 등록 또는 해제
function setWishList(contentId, userId, success, fail) {
    api.get(`/wishList/${userId}/${contentId}`).then(success).catch(fail);
}


//유저 한명에 대한 위시리스트 목록
function getUserWishList(userId, success, fail) {
    api.get(`/wishList/${userId}`).then(success).catch(fail);
}


//contentId에 대한 등록된 wish개수
function getAttractionWishCount(contentId, success, fail) {
    api.get(`/wishList/getCount/${contentId}`).then(success).catch(fail);
}

export {
    setWishList, getUserWishList, getAttractionWishCount
};
