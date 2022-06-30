<template>
  <Form :title="$route.meta.title" :postId="$route.params.id"></Form>
</template>

<script lang="ts" setup>
import Form from "/@/views/admin/posts/common/Form.vue"
</script>

<style scoped>

</style>
