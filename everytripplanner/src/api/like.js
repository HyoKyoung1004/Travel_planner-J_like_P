import { tripInstance } from "./http.js";

const api = tripInstance();

//content에 좋아요 
function setLikeAttraction(contentId, userId, success, fail) {
    api.get(`/like/${userId}/${contentId}`).then(success).catch(fail);
}

export {
    setLikeAttraction
};
