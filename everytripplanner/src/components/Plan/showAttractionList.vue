<template>
    <div id="attraction">
        <attraction-list :latitude="latitude" :longitude="longitude"></attraction-list>
        <show-attraction @mom="goto" ></show-attraction>
        
    </div>
</template>

<script>
import AttractionList from "@/components/Plan/items/AttractionList.vue"
import ShowAttraction from "@/components/Plan/items/ShowAttraction.vue"
export default {    
    components: {
        AttractionList,
        ShowAttraction,
    },
    data() {
        return {
            message: '',
            
        };
    },
    props:[
        'latitude',
        'longitude'
        ]
    ,
    created() {},
    methods: {
        goto(child){
            this.$emit("mom",child);
        }
    },
};
</script>

<style scoped>

</style>