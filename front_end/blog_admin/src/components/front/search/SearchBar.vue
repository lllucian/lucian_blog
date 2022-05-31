<template>
  <header class="searchBar">
    <div class="searchForm" role="search">
      <label class="search-label" v-if="!isSearching">
        <Icon icon="bi:search" :size="20"></Icon>
      </label>
      <div class="search-input-loading" v-else>
        <Icon icon="icon-park:loading-four" :size="20"></Icon>
      </div>
      <input type="text" class="search-input" placeholder="输入关键词" autocomplete="false" ref="searchBox" v-model="pageKeyword" v-bind="$attrs">
    </div>
  </header>
</template>
<script lang="ts" setup>
import Icon from "../../common/basic/Icon.vue"
import {onMounted, provide, ref, unref, watch} from "vue";
import {getRequest} from "/@/requests";

declare type Nullable<T> = T | null;

const pageKeyword = ref<String>('');


const isSearching = ref(false);

const searchBox = ref<Nullable<HTMLElement>>(null);

onMounted(() => {
  // searchBox.value.focus();
  unref(searchBox)?.focus();
});

</script>
<style scoped>
.searchBar {
  display: flex;
  padding: 12px 12px;
}

.searchForm {
  align-items: center;
  background: #fff;
  border-radius: 4px;
  box-shadow: inset 0 0 0 2px #ff895d;
  display: flex;
  height: 56px;
  padding: 0 12px;
  position: relative;
  width: 100%;
}

.search-label {
  align-items: center;
  color: #ff895d;
  display: flex;
  justify-content: center;
}

.search-input {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background: transparent;
  border: 0;
  color: #1c1e21;
  flex: 1;
  font: inherit;
  font-size: 1.2em;
  height: 100%;
  outline: none;
  padding: 0 0 0 8px;
  width: 80%;
}

label {
  margin: unset !important;
}

.search-input-loading {
  -webkit-animation:load 1.1s infinite linear;
}

@-webkit-keyframes load{
  from{
    transform: rotate(0deg);
  }
  to{
    transform: rotate(360deg);
  }
}
</style>