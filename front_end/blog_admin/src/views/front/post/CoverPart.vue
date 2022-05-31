<template>
  <Waypoint @change="onChange" :style="{backgroundImage: `url('${props.imageUrl}')`, backgroundRepeat: 'round'}">
    <div class="hero-wrap"
         :style="{height: pageHeight, backgroundPosition: imagePosition}" v-scroll="handleScroll">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
             data-scrollax-parent="true" :style="{height: pageHeight}">
          <div class="col-md-9 text-center ftco-animate" :class="animateClass">
            <p class="breadcrumbs"><span class="mr-2">
              <router-link to="/">首页</router-link></span><span>文章</span></p>
            <h1 class="mb-3 bread">{{props.title}}</h1>
            <h3 class="mb-3 bread" >{{props.description}}</h3>
          </div>
        </div>
      </div>
    </div>
  </Waypoint>
</template>
<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {Waypoint} from "vue-waypoint";

const props = defineProps<{
  imageUrl: String,
  title: String,
  description: String
}>();

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
const pageHeight = ref(window.innerHeight + "px");
const getPageRiseHeight = async () => {
  pageHeight.value = window.innerHeight + "px";
};

const animateClass = ref<Array<String>>([]);
const onChange = async (waypointState: any) => {
  waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
}

const imagePosition = ref("50% 50%");

const handleScroll = () => {
  const st = window.scrollY;
  imagePosition.value = "50% " + st + "px";
}
onMounted(() => {
  window.addEventListener("resize", getPageRiseHeight);
});
</script>