import { tripInstance } from "./http.js";

const api = tripInstance();

function setWishList(contentId, userId, success, fail) {
    api.get(`/wishList/${userId}/${contentId}`).then(success).catch(fail);
}

export {
    setWishList
};
