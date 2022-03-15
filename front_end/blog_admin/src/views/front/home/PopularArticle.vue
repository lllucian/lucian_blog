<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Popular Articles</h3>
      <div class="block-21 mb-4 d-flex">
        <a class="blog-img mr-4" style="background-image: url(/src/assets/image_1.jpg);"></a>
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
        <a class="blog-img mr-4" style="background-image: url(/src/assets/image_1.jpg);"></a>
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
        <a class="blog-img mr-4" style="background-image: url(/src/assets/image_1.jpg);"></a>
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
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Waypoint} from "vue-waypoint";

export default defineComponent({
  components: {
    Waypoint
  },
  setup(){
    const animateClass: any = ref([]);
    const onChange = async (waypointState: any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return {onChange, animateClass};
  }
})
</script>