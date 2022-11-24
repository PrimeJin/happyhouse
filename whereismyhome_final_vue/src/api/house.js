import { houseInstance } from "./index.js";

const house = houseInstance();

function sidoList(success, fail) {
  house.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  house.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  house.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function searchList(params, success, fail) {
  house.get(`/map/search`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, searchList };
