<template>
  <Teleport to="body">
    <div class="searchBox" :style="{zIndex: 9999}" @click.self="closeModal" @keydown.esc.prevent="$emit('close')" v-bind="$attrs" v-scroll="writeScroll">
      <div class="searchModal">
        <SearchBar v-model="keyword" v-bind="$attrs"/>
        <SearchResult/>
        <SearchFooter/>
      </div>
    </div>
  </Teleport>
</template>
<script lang="ts" setup>
import SearchBar from "./SearchBar.vue"
import SearchResult from "./SearchResult.vue"
import SearchFooter from "/@/components/front/search/SearchFooter.vue";
import {onMounted, ref} from "vue";
import { onKeyStroke } from '@vueuse/core';

let scrollTop = 0;
const emit = defineEmits<{(e: 'close') :void}>();

const keyword = ref<String>('');

const vScroll = {
  mounted: (el: any, binding: any) => {
    let f = function (evt: any) {
      if (binding.value(evt, el)) {
        window.removeEventListener('scroll', f)
      }
    }
    window.addEventListener('scroll', f)
  }
}

const writeScroll = (() => {
  scrollTop = document.documentElement.scrollTop;
});

const closeModal = (() => {
  emit('close')
});

onMounted(() => {
  scrollTop = document.documentElement.scrollTop;
});

onKeyStroke("Escape", closeModal);
</script>
<style scoped>
.searchBox {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  overflow: auto;
}

.searchModal {
  background: #f5f6f7;
  border-radius: 6px;
  max-width: 560px;
  position: relative;
  margin: 60px auto auto;
  box-shadow: inset 1px 1px 0 0 hsla(0,0%,100%,0.5),0 3px 8px 0 #555a64;
  flex-direction: column;
}
</style>