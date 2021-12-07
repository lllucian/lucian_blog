<template>
  <Form :title="$route.meta.title" :postId="$route.params.id"></Form>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import Form from "/@/views/admin/posts/common/Form.vue"

export default defineComponent ({
  components: {
    Form
  },
  setup(){

  }
})
</script>

<style scoped>

</style>
