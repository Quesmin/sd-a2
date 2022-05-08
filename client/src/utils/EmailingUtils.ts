import { EmailDto } from "../models/dto/EmailDto";
import { Order } from "../models/entities/Order";
import { OrderedFood } from "../models/entities/OrderedFood";

export const getEmailOrderContentMessage = (orderedFoods: OrderedFood[]) => {
  let content = `ORDER CONTENT: <br>--------------------<br><br>`;
  let total = 0;
  orderedFoods.forEach((e) => {
    total += e.quantity * e.food.price;
    content += `${e.quantity} X ${e.food.name} ----- ${
      e.quantity * e.food.price
    } RON<br><br>`;
  });
  content += `<br>--------------------<br>TOTAL: ${total} RON<br><br>`;
  return content;
};

export const getEmailTemplateObject = (
  customerId: string,
  adminEmail: string,
  restaurantId: string,
  order: Order
) => {
  console.log(getEmailOrderContentMessage(order.orderedFoods));
  return {
    recipient: adminEmail,
    order_id: order.id,
    restaurant_id: restaurantId,
    customer_id: customerId,
    order_content: getEmailOrderContentMessage(order.orderedFoods),
  } as EmailDto;
};
