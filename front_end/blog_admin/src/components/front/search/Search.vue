<template>
  <div class="sidebar-box">
    <div action="#" class="search-form">
      <div class="form-group">
        <span class="icon iconify" data-icon="bi:search"></span>
        <input type="text" class="form-control" placeholder="文章关键词" @click.stop="openDialog">
      </div>
    </div>
  </div>
  <SearchModel v-if="isOpen" v-on:close="closeDialog" v-model="keyword"/>
</template>
<script lang="ts" setup>
import SearchModel from "./SearchModal.vue";
import {provide, ref, watch} from "vue";
import {getRequest} from "/@/requests";
const isOpen = ref(false);

const keyword = ref<String>('');

const searchResult = ref([]);

const openDialog = (() => {
  isOpen.value = true;
});

watch(keyword, async (newValue: String, oldValue: String) => {
  if (newValue.trim() == "") searchResult.value = [];
  const data:any = await getRequest(`/blog/search/${newValue}`);
  if (data.data) {
    searchResult.value = data.data;
  } else {
    searchResult.value = [];
  }
});

provide('searchResult', searchResult);

const closeDialog = (() => {
  isOpen.value = false;
  keyword.value = '';
  searchResult.value = [];
})
</script>