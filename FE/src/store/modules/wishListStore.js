import {getUserWishList } from "@/api/wishList";


const wishListStore = {
    namespaced: true,
    state: {
        wishList:{},

    },
    mutations:{
        SET_GET_WISHLIST(state,data){
            state.wishList=data;
            console.log("WISHLISH",state.wishList);
        },
    },actions:{
        getMyWishList({commit},userId){
            getUserWishList(
                userId,
                (data)=>{
                    console.log(data);
                    commit("SET_GET_WISHLIST",data.data);
                },
                (error)=>{
                    console.log(error);
                }
            )
        }

    }

}

export default wishListStore;