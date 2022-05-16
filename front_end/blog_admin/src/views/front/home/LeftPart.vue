<template>
  <div class="col-lg-8">
    <div class="row">
      <div class="col-md-12" v-for="(post, index) in pageData.data" :key="index">
        <postPart :post="post"/>
      </div>
      <div class="row mt-5">
        <div class="col text-center">
          <FrontPagination v-model:currentPage="currentPage" :totalPages="pageData.pages" v-if="pageData.total !== 0"></FrontPagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref, watch} from "vue";
import postPart from "/@/views/front/home/PostPart.vue";
import {apiFormData} from './page'
import FrontPagination from "/@/components/data/FrontPagination.vue";

export default defineComponent({
  components: {
    postPart,
    FrontPagination
  },
  setup() {
    const currentPage = ref(1);
    const pageData = ref({
        data: [],
        total: 0,
        current: 1,
        size: 10,
        pages: 0
    })
    onMounted(async () => {
      const {records, total, current, size, pages} = await apiFormData(1);
      pageData.value.data = records;
      pageData.value.total = total;
      pageData.value.current = current;
      pageData.value.size = size;
      pageData.value.pages = pages;
      currentPage.value = current;
    });
    watch(currentPage, async(newValue: Number, oldValue: Number) => {
      const {records, total, current, size, pages} = await apiFormData(newValue);
      pageData.value.data = records;
      pageData.value.total = total;
      pageData.value.current = current;
      pageData.value.size = size;
      pageData.value.pages = pages;
      currentPage.value = current;
    })
    return {pageData, currentPage}
  }
})
</script>