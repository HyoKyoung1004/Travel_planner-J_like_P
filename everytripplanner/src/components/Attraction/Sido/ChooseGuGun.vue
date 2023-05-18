<template>
    <div>
        <tr>
            <td>{{ ii.gugun_name }}</td>
        </tr>

    </div>
</template>

<script>
export default {
    name: 'ChooseGugun',
    components: {},
    data() {
        return {
            message: '',
        };
    },
    props: {
        ii:Object,
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>