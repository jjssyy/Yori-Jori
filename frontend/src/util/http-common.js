import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://I5C205.p.ssafy.io/:8080/', //백엔드 포트번호
  headers: {
    'Content-type': 'application/json',
  },
});
