import { tripInstance } from "./http.js";

const api = tripInstance();

function commentDetail(contentId, success, fail) {
    api.get(`/comment/${contentId}`).then(success).catch(fail);
}

export { commentDetail };