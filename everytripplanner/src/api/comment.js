import { tripInstance } from "./http.js";

const api = tripInstance();

function commentDetailFunction(contentId, success, fail) {
    console.log(contentId.contentId);
    api.get(`/comment/${contentId.contentId}`).then(success).catch(fail);
}

export { commentDetailFunction };