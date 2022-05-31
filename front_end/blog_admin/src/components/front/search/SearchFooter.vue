<template>
  <footer class="DocSearch-Footer">
    <ul class="DocSearch-Commands">
      <li><span class="DocSearch-Commands-Key"><Icon icon="fluent:arrow-enter-left-24-filled"/></span><span
          class="DocSearch-Label">确认</span>
      </li>
      <li><span class="DocSearch-Commands-Key"><Icon icon="akar-icons:arrow-up"></Icon></span><span
          class="DocSearch-Commands-Key"><Icon icon="akar-icons:arrow-down"></Icon></span><span
          class="DocSearch-Label">切换</span>
      </li>
      <li><span class="DocSearch-Commands-Key"><Icon icon="mdi:keyboard-esc"></Icon></span><span
          class="DocSearch-Label">关闭</span></li>
    </ul>
  </footer>
</template>
<script lang="ts" setup>
import Icon from "../../common/basic/Icon.vue"
</script>
<style scoped>
.DocSearch-Footer {
  align-items: center;
  background: #fff;
  border-radius: 0 0 8px 8px;
  box-shadow: 0 -1px 0 0 #e0e3e8,0 -3px 6px 0 rgba(69,98,155,0.12);
  display: flex;
  flex-direction: row-reverse;
  flex-shrink: 0;
  height: 44px;
  justify-content: left;
  padding: 0 12px;
  position: relative;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  width: 100%;
  z-index: 300;
}
.DocSearch-Commands {
  color: #969faf;
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}
.DocSearch-Commands li:not(:last-of-type) {
  margin-right: .8em;
}
.DocSearch-Commands li {
  align-items: center;
  display: flex;
}
.DocSearch-Commands-Key {
  align-items: center;
  background: linear-gradient(-225deg,#d5dbe4,#f8f8f8);
  border-radius: 2px;
  box-shadow: inset 0 -2px 0 0 #cdcde6,inset 0 0 1px 1px #fff,0 1px 2px 1px rgba(30,35,90,0.4);
  display: flex;
  height: 18px;
  justify-content: center;
  margin-right: .4em;
  padding-bottom: 2px;
  width: 20px;
}
</style>