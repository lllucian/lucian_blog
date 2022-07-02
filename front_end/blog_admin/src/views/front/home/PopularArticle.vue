<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Popular Articles</h3>
      <div class="block-21 mb-4 d-flex">
        <a class="blog-img mr-4" :style="{backgroundImage: defaultPicPath}"></a>
        <div class="text">
          <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a>
          </h3>
          <div class="meta">
            <div><a href="#"><span class="icon-calendar"></span> Oct. 04, 2018</a></div>
            <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
            <div><a href="#"><span class="icon-chat"></span> 19</a></div>
          </div>
        </div>
      </div>
      <div class="block-21 mb-4 d-flex">
        <a class="blog-img mr-4" :style="{backgroundImage: defaultPicPath}"></a>
        <div class="text">
          <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a>
          </h3>
          <div class="meta">
            <div><a href="#"><span class="icon-calendar"></span> Oct. 04, 2018</a></div>
            <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
            <div><a href="#"><span class="icon-chat"></span> 19</a></div>
          </div>
        </div>
      </div>
      <div class="block-21 mb-4 d-flex">
        <a class="blog-img mr-4" :style="{backgroundImage: defaultPicPath}"></a>
        <div class="text">
          <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a>
          </h3>
          <div class="meta">
            <div><a href="#"><span class="icon-calendar"></span> Oct. 04, 2018</a></div>
            <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
            <div><a href="#"><span class="icon-chat"></span> 19</a></div>
          </div>
        </div>
      </div>
    </div>
  </Waypoint>
</template>
<script lang="ts" setup>
import {ref} from "vue";
import {Waypoint} from "vue-waypoint";

import defaultPic from '/@/assets/image_1.jpg'

const defaultPicPath = `url(${defaultPic})`;

const animateClass: any = ref<Array<string>>([]);
const onChange = async (waypointState: any) => {
  waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
}


</script>