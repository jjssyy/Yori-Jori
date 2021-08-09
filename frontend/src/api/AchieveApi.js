
import http from '@/util/http-common';
import { mapState } from 'vuex';
import { mapActions } from 'vuex';

const AchievemasterList = (data, callback, errorCallback) => {
  http
    .get('/achieve/list')
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const AchievemasterCount = (data, callback, errorCallback) => {
  http
    .get('/achieve/mastercount')
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const AchieveApi = {
  AchievemasterList: (data, callback, errorCallback) => AchievemasterList(data, callback, errorCallback),
  AchievemasterCount: (data, callback, errorCallback) => AchievemasterCount(data, callback, errorCallback),
};
export default AchieveApi;