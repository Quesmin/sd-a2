import { AnyAction } from "@reduxjs/toolkit";
import { ThunkAction } from "redux-thunk";
import { FoodDto } from "../../models/dto/FoodDto";
import { RestaurantDto } from "../../models/dto/RestaurantDto";
import {
  addFoodRequest,
  addRestaurantRequest,
  getRestaurantsRequest,
} from "../../services/RestaurantsService";
import { loginRequest, signupRequest } from "../../services/UserService";
import { setCurrentRestaurant, setRestaurants } from "../restaurants/slice";
import { restaurantsInitialState } from "../restaurants/state";
import { RootState } from "../store";
import {
  addFoodToRestaurant,
  addRestaurantToUser,
  login,
  logout,
  setIsInputError,
} from "./slice";

export const loginUser =
  (
    email: string,
    password: string
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const loginResponse = await loginRequest(email, password);
      dispatch(login(loginResponse));

      const getRestaurantsResponse = await getRestaurantsRequest();

      dispatch(setRestaurants(getRestaurantsResponse));
      dispatch(setIsInputError(false));
    } catch (error) {
      console.log("Error:", error);
      dispatch(setIsInputError(true));
    }
  };

export const signupUser =
  (
    email: string,
    password: string
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const response = await signupRequest(email, password);

      dispatch(login(response));
    } catch (error) {
      console.log("Error:", error);
    }
  };

export const logoutUser =
  (): ThunkAction<void, RootState, null, AnyAction> => async (dispatch) => {
    try {
      dispatch(logout());
      dispatch(setRestaurants(restaurantsInitialState.restaurants));
      dispatch(setCurrentRestaurant(restaurantsInitialState.currentRestaurant));
    } catch (error) {
      console.log("Error:", error);
    }
  };

export const addRestaurant =
  (
    name: string,
    location: string,
    adminId: string,
    callback: () => void
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const data = {
        name: name,
        location: location,
        adminId: adminId,
      } as RestaurantDto;
      const response = await addRestaurantRequest(data);

      dispatch(addRestaurantToUser(response));
      dispatch(setIsInputError(false));
      callback();
    } catch (error) {
      dispatch(setIsInputError(true));
      console.log("Error:", error);
    }
  };

export const addFood =
  (
    name: string,
    description: string,
    category: string,
    price: number,
    restaurantId: string,
    callback: () => void
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const data = {
        name: name,
        description: description,
        category: category,
        price: price,
        restaurantId: restaurantId,
      } as FoodDto;
      const response = await addFoodRequest(data);

      dispatch(
        addFoodToRestaurant({ food: response, restaurantId: restaurantId })
      );
      dispatch(setIsInputError(false));
      callback();
    } catch (error) {
      console.log("Error:", error);
      dispatch(setIsInputError(true));
    }
  };
