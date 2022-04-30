<template>
  <div class="block-27">
    <ul>
      <li><a href="javascript: void(0)" v-if="currentPage - 1 > 0" @click="handlePageChange(1)">&lt;&lt;</a></li>
      <li><a href="javascript: void(0)" v-if="currentPage - 1 > 0" @click="handlePageChange(currentPage - 1)">&lt;</a></li>
      <li v-for="page in range()" :key="page" :class="currentPage === page ? ['active'] : []"><a href="javascript: void(0)" @click="handlePageChange(page)">{{ page }}</a></li>
      <li><a href="javascript: void(0)" v-if="currentPage !== totalPages" @click="handlePageChange(currentPage + 1)">&gt;</a></li>
      <li><a href="javascript: void(0)" v-if="currentPage !== totalPages " @click="handlePageChange(totalPages)">&gt;&gt;</a></li>
    </ul>
  </div>
</template>
<script lang="ts">
import {defineComponent, toRefs} from "vue";

export default defineComponent({
  props: {
    currentPage: {
      type: Number,
      default: 1,
      required: true
    },
    totalPages: {
      type: Number,
      default: 1,
      required: true
    },
    showPageNumber: {
      type: Number,
      default: 5
    }
  },
  emits: ['update:currentPage'],
  setup(props, {emit}) {
    const {currentPage, totalPages, showPageNumber} = toRefs(props);

    const start = (() => {
      // 如果总页数小于等于showPageNumber则start page始终为1
      if (showPageNumber.value >= totalPages.value) {
        return 1;
      }
      const startPage =  currentPage.value - showPageNumber.value;
      // 如果总页数-开始页小于showPageNumber 则为totalPages - showPageNumber
      if (totalPages.value - startPage < totalPages.value){
        return totalPages.value - showPageNumber.value;
      }
      return startPage;
    });

    const end = (() => start() + showPageNumber.value > totalPages.value ? totalPages.value : start() + showPageNumber.value);
    const range = (() => {
      const pages = [];
      for (let i = start(); i <= end(); i++) {
        pages.push(i);
      }
      return pages;
    });

    const handlePageChange = ((page:Number) => emit('update:currentPage', page))
    return {range, handlePageChange, currentPage, totalPages}
  }
});
</script>