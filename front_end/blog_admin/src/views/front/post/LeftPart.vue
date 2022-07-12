<template>
  <Waypoint @change="onChange" class="col-lg-8">
    <div class="ftco-animate" :class="animateClass">
      <div v-html="blogData()"></div>
    </div>
  </Waypoint>
</template>
<script lang="ts" setup>
import {inject, onMounted, ref, unref} from "vue";
import {Waypoint} from "vue-waypoint";


const blogData = () => {
  let data = inject<{content: string}>('blogData');
  return data ? unref(data).content : '';
}

const pageHeight = ref(window.innerHeight + "px");
const getPageRiseHeight = async () => {
  pageHeight.value = window.innerHeight + "px";
};

const animateClass: any = ref([]);
const onChange = async (waypointState: any) => {
  waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
}


onMounted(() => {
  window.addEventListener("resize", getPageRiseHeight);
});

</script>