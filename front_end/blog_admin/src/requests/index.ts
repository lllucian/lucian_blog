import axios, { AxiosInstance } from "axios";
import { ElMessage } from "element-plus";

const instance: AxiosInstance = axios.create();

// 响应拦截器
instance.interceptors.response.use(response => {
    if (response.data.status! !== 200) {
        ElMessage.error({message: response.data.msg});
        return;
    }

    if (response.data.msg){
        ElMessage.success({message: response.data.msg});
    }
    return response.data;
}, error => {
    ElMessage.error({message: '请求错误！'});
});

export const postRequest = (url: string, params: object) => {
    return instance.post(url, params);
}