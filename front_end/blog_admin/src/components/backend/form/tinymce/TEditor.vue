<template>
  <div class="tinymce-box" :style="{width: '100%'}">
    <Editor v-model="modelValue" v-bind="$attrs" :init="init"></Editor>
    <el-input v-model="modelValue" v-bind="$attrs" v-show="false"></el-input>
  </div>
</template>

<script lang="ts" setup>

//引入tinymce编辑器
import Editor from '@tinymce/tinymce-vue'

//引入node_modules里的tinymce相关文件文件
import tinymce from 'tinymce/tinymce' //tinymce默认hidden，不引入则不显示编辑器
import 'tinymce/themes/silver'  //编辑器主题，不引入则报错
import 'tinymce/icons/default'  //引入编辑器图标icon，不引入则不显示对应图标
import 'tinymce/models/dom/index'
import 'tinymce/plugins/wordcount/index'
import 'tinymce/plugins/preview'
import 'tinymce/plugins/image'
import 'tinymce/plugins/fullscreen'
import  'tinymce/plugins/code'
import 'tinymce/plugins/table'

import {postRequest} from "/@/requests";
import {onMounted, ref} from "vue";
import {BuildPropType, PropWrapper} from "element-plus/es/utils";

const {
  modelValue = '', toolbar = 'fullscreen undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | \
                styleselect formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat | \
                table image media charmap emoticons hr pagebreak insertdatetime print preview | code selectall searchreplace visualblocks | indent2em lineheight formatpainter axupimgs',
  plugins = 'wordcount preview image fullscreen code table',
  disabled = true
} = defineProps<{
  modelValue: BuildPropType<PropWrapper<string | number>, unknown, unknown>,
  toolbar?: String | Array<String>,
  plugins?: String | Array<String>,
  disabled?: Boolean
}>();

const init = ref({
  language_url: '/tinymce/langs/zh_CN.js',  //引入语言包文件
  language: 'zh_CN',  //语言类型
  skin_url: '/tinymce/skins/ui/oxide',  //皮肤：浅色
  content_css: '/tinymce/skins/ui/oxide/content.css',
  emoticons_database_url: '/tinymce/emoticons/js/emojis.js',
  toolbar: toolbar,
  plugins: plugins,
  toolbar_sticky: true,
  fontsize_formats: '12px 14px 16px 18px 20px 22px 24px 28px 32px 36px 48px 56px 72px',
  font_formats: '微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;' +
      '苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;宋体=simsun,serif;仿宋体=FangSong,serif;' +
      '黑体=SimHei,sans-serif;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;',  //字体样式
  lineheight_formats: "0.5 0.8 1 1.2 1.5 1.75 2 2.5 3 4 5",  //行高配置，也可配置成"12px 14px 16px 20px"这种形式
  height: 400,  //注：引入autoresize插件时，此属性失效
  placeholder: '在这里输入文字',
  width: '100%',
  branding: false,
  elementpath: true,
  toolbar_mode: 'sliding',

  content_style: "img {max-width:100%;}",
  paste_data_images: true,
  images_upload_handler: async (blobInfo:any, success:any, failure:any) => {
    if (blobInfo.blob().size / 1024 / 1024 > 2) {
      failure("上传失败，图片大小请控制在 2M 以内")
    } else {
      let params = new FormData()
      params.append('file', blobInfo.blob())
      params.append('bucketName', 'pic')
      const data = await postRequest("/admin/upload_file/upload", params)
      if (data && data.data) return success(data.data.fileUrl);
      return failure("上传失败");
    }
  }
})

onMounted(() => tinymce.init({}));

</script>

<style lang="less">
.is-error .tox-tinymce {
  border-color: var(--el-color-danger);
}
.tox-toolbar {
  max-width: 100% !important;
}
</style>
