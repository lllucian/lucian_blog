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

// 引入编辑器插件（基本免费插件都在这儿了）
import 'tinymce/plugins/advlist'  //高级列表
import 'tinymce/plugins/anchor'  //锚点
import 'tinymce/plugins/autolink'  //自动链接
import 'tinymce/plugins/autoresize'  //编辑器高度自适应,注：plugins里引入此插件时，Init里设置的height将失效
import 'tinymce/plugins/autosave'  //自动存稿
import 'tinymce/plugins/charmap'  //特殊字符
import 'tinymce/plugins/code'  //编辑源码
import 'tinymce/plugins/codesample'  //代码示例
import 'tinymce/plugins/directionality'  //文字方向
import 'tinymce/plugins/emoticons'  //表情
import 'tinymce/plugins/fullpage'  //文档属性
import 'tinymce/plugins/fullscreen'  //全屏
import 'tinymce/plugins/help'  //帮助
import 'tinymce/plugins/hr'  //水平分割线
import 'tinymce/plugins/image'  //插入编辑图片
import 'tinymce/plugins/importcss'  //引入css
import 'tinymce/plugins/insertdatetime'  //插入日期时间
import 'tinymce/plugins/link'  //超链接
import 'tinymce/plugins/lists' //列表插件
import 'tinymce/plugins/media' //插入编辑媒体
import 'tinymce/plugins/nonbreaking' //插入不间断空格
import 'tinymce/plugins/pagebreak' //插入分页符
import 'tinymce/plugins/paste' //粘贴插件
import 'tinymce/plugins/preview'//预览
import 'tinymce/plugins/print'//打印
import 'tinymce/plugins/quickbars'  //快速工具栏
import 'tinymce/plugins/save'  //保存
import 'tinymce/plugins/searchreplace'  //查找替换
import 'tinymce/plugins/spellchecker'  //拼写检查，暂未加入汉化，不建议使用
import 'tinymce/plugins/tabfocus'  //切入切出，按tab键切出编辑器，切入页面其他输入框中
import 'tinymce/plugins/table'  //表格
import 'tinymce/plugins/template'  //内容模板
import 'tinymce/plugins/textcolor'  //文字颜色
import 'tinymce/plugins/textpattern'  //快速排版
import 'tinymce/plugins/toc'  //目录生成器
import 'tinymce/plugins/visualblocks'  //显示元素范围
import 'tinymce/plugins/visualchars'  //显示不可见字符
import 'tinymce/plugins/wordcount'
import {postRequest} from "/@/requests";
import {defineComponent, onMounted, ref} from "vue";  //字数统计

const {
  modelValue = '', toolbar = 'fullscreen undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | \
                styleselect formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat | \
                table image media charmap emoticons hr pagebreak insertdatetime print preview | code selectall searchreplace visualblocks | indent2em lineheight formatpainter axupimgs',
  plugins = 'print preview emoticons searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount textpattern autosave autoresize',
  disabled = true
} = defineProps<{
  modelValue: String,
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
  toolbar_mode: 'wrapper',

  content_style: "img {max-width:100%;}",
  paste_data_images: true,
  images_upload_handler: async (blobInfo:any, success:any, failure:any) => {
    if (blobInfo.blob().size / 1024 / 1024 > 2) {
      failure("上传失败，图片大小请控制在 2M 以内")
    } else {
      let params = new FormData()
      params.append('file', blobInfo.blob())
      params.append('bucketName', 'pic')
      const data = await postRequest("api/admin/upload_file/upload", params)
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
</style>
