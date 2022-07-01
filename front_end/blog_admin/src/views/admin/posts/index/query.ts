import { postRequest } from "/@/requests";

export interface pageInfo {
    size: number;
    current: number;
    total: number;
}
export interface queryConditionsByPageInterface extends pageInfo{
    categories: number[];
    tags: number[];
    title: string;
    created: string[];
    updated: string[];
    size: number;
    current: number;
    total: number;
    records: any[];
  }

export let queryConditionsByPage: queryConditionsByPageInterface;

export const setQueryConditions = (
  conditions: queryConditionsByPageInterface
) => {
  queryConditionsByPage = conditions;
};

export const setPageConditions = (pageInfo: pageInfo) => {
    queryConditionsByPage = {...queryConditionsByPage, ...pageInfo}
}

export const getPagesize = () => {
    return queryConditionsByPage.size || 10;
}


export const apiFormData = async () => {
    const data = await postRequest("/admin/posts", queryConditionsByPage);

    if(data.data && data.data.records){
        return {
            records: data.data.records,
            total: data.data.total,
            current: data.data.current,
            size: data.data.size,
        };
    } else {
        return {records: [], total: 0, current: 1, size: data.data.size};
    }
}
