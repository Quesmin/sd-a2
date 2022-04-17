import { AnyAction } from "@reduxjs/toolkit";
import { ThunkAction } from "redux-thunk";
import {
  changeOrderStatusRequest,
  getRestaurantsRequest,
} from "../../services/RestaurantsService";
import { RootState } from "../store";
import { setOrderStatus, setRestaurants } from "./slice";

export const getRestaurants =
  (): ThunkAction<void, RootState, null, AnyAction> => async (dispatch) => {
    try {
      const response = await getRestaurantsRequest();

      dispatch(setRestaurants(response));
    } catch (error) {
      console.log("Error:", error);
    }
  };

export const changeOrderStatus =
  (
    orderId: string,
    orderStatus: string
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const response = await changeOrderStatusRequest(orderId, orderStatus);

      dispatch(setOrderStatus(response));
    } catch (error) {
      console.log("Error:", error);
    }
  };
