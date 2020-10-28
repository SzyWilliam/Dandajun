<template>
    <v-container>
        <v-btn
        id="app"
        color="light-blue lighten-5"
        v-on:click="defaultValue += 1"
        >Button</v-btn>
        <markdown-editor
            :value="defaultValue"
        ></markdown-editor>
    </v-container>
    
</template>

<script>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
    components: {
        'markdown-editor': mavonEditor,
    },

    data() {
        return {
            defaultValue: "CLSTSL",
        }
    },

    methods: {
        print: function()
        {
            this.defaultValue += 1
            console.log("he")
        }
    }

    
}
</script>

<style scoped>

</style>