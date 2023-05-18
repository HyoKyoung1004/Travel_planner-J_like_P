import { tripInstance } from "./http.js";

const api = tripInstance();


async function login(user, success, fail) {
    const headers = {
        'Content-type': 'application/json'
        }
    await api.post(`/user/login`, JSON.stringify(user),{headers}).then(success).catch(fail);
}
export { login };