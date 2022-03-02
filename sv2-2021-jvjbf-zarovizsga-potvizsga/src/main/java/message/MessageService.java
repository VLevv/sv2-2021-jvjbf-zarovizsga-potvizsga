package message;

public class MessageService {
    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void registerUser(String username){
        if(userRepository.findUserByName(username).isPresent()){
            throw new IllegalArgumentException("Username is already taken: "+username);
        }else {
            userRepository.insertUser(username);
        }
    }

    public void sendMessage(User sender,User receiver,String message){
        if (userRepository.findUserByName(sender.getUsername()).isPresent()&&userRepository.findUserByName(receiver.getUsername()).isPresent()){
            messageRepository.insertMessage(userRepository.findUserByName(sender.getUsername()).get().getId(),userRepository.findUserByName(receiver.getUsername()).get().getId(),message);
        }else {
            throw new IllegalArgumentException("Cannot find one of the users");
        }
    }
}
