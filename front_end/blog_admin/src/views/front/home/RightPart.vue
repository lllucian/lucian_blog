<template>
  <div class="col-lg-4">
    <Waypoint @change="onChange">
      <div class="sidebar ftco-animate" :class="animateClass">
        <Search/>
        <CategoryPart/>
        <PopularArticle/>
        <TagCloud/>
        <ArchivePart/>
        <ParagraphPart/>
      </div>
    </Waypoint>
  </div>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Waypoint} from "vue-waypoint";
import Search from "/@/components/front/search/Search.vue";
import CategoryPart from "/@/views/front/home/CategoryPart.vue";
import PopularArticle from "/@/views/front/home/PopularArticle.vue";
import TagCloud from "/@/views/front/home/TagCloud.vue";
import ArchivePart from "/@/views/front/home/ArchivePart.vue";
import ParagraphPart from "/@/views/front/home/ParagraphPart.vue";

export default defineComponent({
  components: {
    Waypoint,
    Search,
    CategoryPart,
    PopularArticle,
    TagCloud,
    ArchivePart,
    ParagraphPart
  },
  setup() {
    const animateClass: any = ref([]);
    const onChange = async (waypointState: any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return {onChange, animateClass};
  }
})
</script>