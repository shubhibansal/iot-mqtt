import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Scanner;
import java.util.Date;
public class max
{
    public static void main(String[] args)
    {
        Scanner ob=new Scanner(System.in);
        int choice=ob.nextInt();
        switch(choice)
        {case 1:
            System.out.println("Free of cost services");
            service();
            break;
            case 2:
                System.out.println("Proprietary services");

                Scanner sc=new Scanner(System.in);
                System.out.println("Redirect to Payment Portal");

                System.out.println("Enter the credit card number");
                String input=sc.nextLine();
                boolean validity=validateCreditCardNumber(input);
                System.out.println(validity);
                if(validity)
                {
                    System.out.println("Enter the month on card");
                    int month = sc.nextInt();
                    System.out.println("Enter the year");
                    int year = sc.nextInt();
                  
                    //  String date = sc.nextLine();
                    System.out.println("Enter the 3 digit CVV number ");
                    int cvv = sc.nextInt();
                }
                else
                {
                    System.out.println("Enter a valid card number");
                }
                System.out.println("Enter the service you want");
                int serv=sc.nextInt();
                System.out.println("Service required by user"+serv);
                System.out.println("Enter the number of days for which you want the data of respective service");
                int days=sc.nextInt();

                service();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
    private static boolean validateCreditCardNumber(String input)

    {
        int[] creditCardInt =new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            creditCardInt[i] = Integer.parseInt(input.substring(i, i + 1));
        }
        for (int i = creditCardInt.length- 2; i >= 0; i = i - 2) {
            int tempValue = creditCardInt[i];
            tempValue = tempValue * 2;
            if (tempValue > 9) {
                tempValue = tempValue % 10 + i;
            }
            creditCardInt[i] = tempValue;
        }
        int total = 0;
        for (int i=0; i < creditCardInt.length;i++)
        {
            total += creditCardInt[i];
        }
        if (total % 10 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static void  service()
    {
        String topic        = "Pressure";
        String content      = "5 Pascal";
        int qos             = 1;
        String broker       = "tcp://localhost:1883";
        String PubId        = "127.0.0.1";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, PubId,        persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}

