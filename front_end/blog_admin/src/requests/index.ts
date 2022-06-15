import axios, { AxiosInstance, AxiosRequestConfig  } from "axios";
import { ElMessage } from "element-plus";
import {stroage} from "/@/stroage"
const instance: AxiosInstance = axios.create();

// instance.defaults.headers.common['Authorization'] = stroage.getters.getToken;


// 请求拦截器
instance.interceptors.request.use((config: AxiosRequestConfig) => {
    if (stroage.getters.getToken && config.headers)
    config.headers['Authorization'] = `Bearer ${stroage.getters.getToken}`;
    return config;
}, error => {
    ElMessage.error({message: '请求错误！'});
})
// 响应拦截器
instance.interceptors.response.use(response => {
    if (response.data.code !== 200) {
        ElMessage.error({message: response.data.message});
        return;
    }

    if (response.data.message){
        ElMessage.success({message: response.data.message});
    }
    return response.data;
}, error => {
    ElMessage.error({message: '请求错误！'});
});

export const postRequest = (url: string, data?: object) => {
    return instance.post(url, data);
}

export const getRequest = (url: string, params?:object) => {
    return instance.get(url, {params: params});
}

export const deleteRequest = (url: string) => {
    return instance.delete(url);
}

export const putRequest = (url: string, data?:object) => {
    return instance.put(url, data);
}


export const authorizeRequests = (url: string, auth: {username: string, password: string}) => {
    return instance.post(url, {}, {auth: auth})
}
