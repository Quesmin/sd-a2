import { AnyAction } from "@reduxjs/toolkit";
import { ThunkAction } from "redux-thunk";
import { OrderDto } from "../../models/dto/OrderDto";
import { OrderedFoodDto } from "../../models/dto/OrderedFoodDto";
import { FoodOrderItem } from "../../models/entities/FoodOrderItem";
import { placeOrderRequest } from "../../services/BillService";
import { RootState } from "../store";
import { addOrder } from "../user/slice";
import { clearCart } from "../bill/slice";
import { sendOrderEmail } from "../../services/EmailService";
import { getEmailTemplateObject } from "../../utils/EmailingUtils";

export const placeOrder =
  (
    customerId: string,
    adminEmail: string,
    items: FoodOrderItem[],
    restaurantId: string,
    callback: () => void
  ): ThunkAction<void, RootState, null, AnyAction> =>
  async (dispatch) => {
    try {
      const data = {
        customerId: customerId,
        orderedFoods: items.map(
          (foodItem) =>
            ({
              foodId: foodItem.item.id,
              quantity: foodItem.quantity,
            } as OrderedFoodDto)
        ),
        restaurantId: restaurantId,
      } as OrderDto;

      const response = await placeOrderRequest(data);
      await sendOrderEmail(
        getEmailTemplateObject(customerId, adminEmail, restaurantId, response)
      );
      dispatch(addOrder(response));
      dispatch(clearCart());
      callback();
    } catch (error) {
      console.log("Error:", error);
    }
  };
