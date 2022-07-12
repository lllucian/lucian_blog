<template>
  <div>
    <div class="DocSearch-StartScreen" v-if="searchInfo.length === 0">
      <p class="DocSearch-Help">无结果</p>
    </div>
    <div class="DocSearch-Dropdown" ref="scrollWrap" v-else>
        <section class="DocSearch-Hits">
        <ul role="listbox" aria-labelledby="docsearch-label" id="docsearch-menu">
          <li class="DocSearch-Hit" :ref="setRefs(index)" :id="'docsearch-item-' + index" role="option"
              :aria-selected="index === activeIndex"
              :data-index="index" @mouseenter="setActiveIndex"
              v-for="(hitData, index) in searchInfo" :key="index">
            <router-link :to="`/post/${hitData.content.id}`">
              <div class="DocSearch-Hit-Container">
                <div class="DocSearch-Hit-icon">
                  <Icon icon="carbon:document-attachment" :size="20"></Icon>
                </div>
                <div class="DocSearch-Hit-content-wrapper">
                  <span class="DocSearch-Hit-title"
                        v-html="hitData.content.title"></span>
                </div>
                <div class="DocSearch-Hit-action">
                  <Icon icon="fluent:arrow-enter-left-20-regular" :size="20" v-show="index === activeIndex"></Icon>
                </div>
              </div>
            </router-link>
          </li>
        </ul>
      </section>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Icon from "/@/components/common/basic/Icon.vue"
import {inject, nextTick, ref, unref} from "vue";
import {onKeyStroke} from '@vueuse/core'
import {useRefs} from "/@/hook/core/useRefs";
import {useRouter} from "vue-router";
import {useScrollTo} from "/@/hook/event/useScrollTo";

const router = useRouter();

const [refs, setRefs] = useRefs();

const activeIndex = ref(-1);

const scrollWrap = ref();

const setActiveIndex = ((e: any) => {
  const index = e.target.dataset.index;
  activeIndex.value = Number(index);
})


const searchInfo = inject("searchResult", ref<any>([]));
const handleUp = (() => {
  if (!searchInfo.value.length) {
    return;
  }
  activeIndex.value--;
  if (activeIndex.value < 0) {
    activeIndex.value = searchInfo.value.length - 1;
  }
  handleScroll();
});

// Arrow key down
const handleDown = (() => {
  if (!searchInfo.value.length) return;
  activeIndex.value++;
  if (activeIndex.value > searchInfo.value.length - 1) {
    activeIndex.value = 0;
  }
  handleScroll();
});

//Enter key
const handleEnter = (async () => {
  if (!searchInfo.value.length) {
    return;
  }
  const result = unref(searchInfo);
  console.log(result)
  const index = unref(activeIndex);
  if (result.length === 0 || index < 0) {
    return;
  }
  const to = result[index];
  await nextTick();
  await router.push(`/post/${to.id}`);
})

const handleScroll = () => {
  const searchList = unref(refs);
  if (!searchList || !Array.isArray(searchList) || searchList.length === 0 || !unref(scrollWrap)){
    return;
  }

  const index = unref(activeIndex);
  const currentRef = searchList[index];
  if (!currentRef) {
    return;
  }

  const wrapEl = unref(scrollWrap);
  if (!wrapEl) {
    return;
  }

  const scrollHeight = currentRef.offsetTop + currentRef.offsetHeight;
  const wrapHeight = wrapEl.offsetHeight;
  console.log(scrollHeight)
  const { start } = useScrollTo({
    el: wrapEl,
    duration: 100,
    to: scrollHeight - wrapHeight,
  });
  start();
}

onKeyStroke('ArrowUp', handleUp);
onKeyStroke('ArrowDown', handleDown);
onKeyStroke('Enter', handleEnter);
</script>
<style scoped>
.DocSearch-Dropdown {
  max-height: calc(600px - 56px - 12px - 44px);
  min-height: 12px;
  overflow-y: scroll;
  padding: 0 12px;
}

.DocSearch-StartScreen {
  font-size: .9em;
  margin: 0 auto;
  padding: 36px 0;
  text-align: center;
  width: 80%;
}

.DocSearch-Help {
  font-size: .9em;
  margin: 0;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.DocSearch-Dropdown ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.DocSearch-Hit {
  border-radius: 4px;
  display: flex;
  padding-bottom: 4px;
  position: relative;
}

.DocSearch-Hit a {
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 1px 3px 0 #d4d9e1;
  display: block;
  padding-left: 12px;
  width: 100%;
}

.DocSearch-Hit-title :deep(mark) {
  background: none;
  color: #ff895d;
}

.DocSearch-Hit-Container {
  align-items: center;
  color: #444950;;
  display: flex;
  flex-direction: row;
  height: 56px;
  padding: 0 12px 0 0;
}

.DocSearch-Hit-icon {
  color: #969faf;
  stroke-width: 1.4;
}

.DocSearch-Hit-icon {
  height: 20px;
  width: 20px;
}

.DocSearch-Hit-content-wrapper {
  display: flex;
  flex: 1 1 auto;
  flex-direction: column;
  font-weight: 500;
  justify-content: center;
  line-height: 1.2em;
  margin: 0 8px;
  overflow-x: hidden;
  position: relative;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 80%;
}

.DocSearch-Hit-title {
  font-size: .9em;
}

.DocSearch-Hit-path {
  color: #969faf;
  font-size: .75em;
}

.DocSearch-Hit-action {
  align-items: center;
  display: flex;
  height: 22px;
  width: 22px;
}

[aria-selected = true] a {
  background-color: #ff895d;
}

[aria-selected = true] .DocSearch-Hit-icon, [aria-selected = true] .DocSearch-Hit-Container, [aria-selected = true] .DocSearch-Hit-path {
  color: #fff;
}

[aria-selected = true] :deep(mark) {
  color: #fff;
  text-decoration: underline;
}
</style>