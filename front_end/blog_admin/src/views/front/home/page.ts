import {getRequest} from "/@/requests";


export const apiFormData = async (currentPage: Number) => {
    const data = await getRequest(`api/blogs/${currentPage}`);

    if(data.data && data.data.records){
        return {
            records: data.data.records,
            total: data.data.total,
            current: data.data.current,
            size: data.data.size,
            pages: data.data.pages
        };
    } else {
        return {records: [], total: 0, current: 1, size: 10, pages: 0};
    }
}