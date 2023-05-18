<template>
    <div id="attraction">
        <attraction-list ></attraction-list>
        <show-attraction @mom="goto"></show-attraction>
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