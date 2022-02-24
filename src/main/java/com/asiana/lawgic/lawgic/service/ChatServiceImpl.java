package com.asiana.lawgic.lawgic.service;

import com.asiana.lawgic.lawgic.config.ModelMapperConfig;
import com.asiana.lawgic.lawgic.dto.ClientDTO;
import com.asiana.lawgic.lawgic.dto.LawyerDTO;
import com.asiana.lawgic.lawgic.dto.MessageDTO;
import com.asiana.lawgic.lawgic.entity.*;
import com.asiana.lawgic.lawgic.repository.ChatRepository;
import com.asiana.lawgic.lawgic.repository.ClientRepository;
import com.asiana.lawgic.lawgic.repository.ConsultRepository;
import com.asiana.lawgic.lawgic.repository.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    final private LawyerRepository lawyerRepository;
    final private ConsultRepository consultRepository;
    final private ChatRepository chatRepository;
    final private ClientRepository clientRepository;

    public List<LawyerDTO> getAllLawyers() {
        ModelMapper mapper = ModelMapperConfig.getModelMapperInstance();

        Object[] lawyers = lawyerRepository.getAllLawyers();
        List<Lawyer> lawyerList = new ArrayList<>();
        List<LawyerDTO> lawyerDTOList = new ArrayList<>();
        for (Object o : lawyers) {
            lawyerList.add((Lawyer) o);
        }

        for (Lawyer lawyerEntity : lawyerList) {
            LawyerDTO lawyerDTO = mapper.map(lawyerEntity, LawyerDTO.class);
            lawyerDTOList.add(lawyerDTO);
        }

        return lawyerDTOList;

    }

    @Override
    public List<ClientDTO> getClientsByLawyerId(Long lawyerId) {
        ModelMapper mapper = ModelMapperConfig.getModelMapperInstance();

        Object[] clients = consultRepository.getClientsByLawyerId(lawyerId);
        List<Client> clientList = new ArrayList<>();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Object o : clients) {
            clientList.add((Client) o);
        }


        for (Client clientEntity : clientList) {
            ClientDTO clientDTO = mapper.map(clientEntity, ClientDTO.class);
            clientDTOList.add(clientDTO);
        }

        return clientDTOList;
    }

    @Override
    public List<LawyerDTO> getLawyersByClientId(Long clientId) {
        ModelMapper mapper = ModelMapperConfig.getModelMapperInstance();

        Object[] lawyers = consultRepository.getLawyersByClientId(clientId);
        List<Lawyer> lawyerList = new ArrayList<>();
        List<LawyerDTO> lawyerDTOList = new ArrayList<>();
        for (Object o : lawyers) {
            lawyerList.add((Lawyer) o);
        }

        for (Lawyer lawyerEntity : lawyerList) {
            LawyerDTO lawyerDTO = mapper.map(lawyerEntity, LawyerDTO.class);
            lawyerDTOList.add(lawyerDTO);
        }

        return lawyerDTOList;
    }



    @Override
    public Consult getConsultByLawyerAndClientId(Long lawyerId, Long clientID) {
        Consult consult = consultRepository.getConsultByLawyerAndClientId(lawyerId, clientID);
        return consult;
    }

    @Override
    public List<MessageDTO> getMessageByChatId(Long chatId) {
        ModelMapper mapper = ModelMapperConfig.getModelMapperInstance();
        Chat chat = chatRepository.getMessagesByChatId(21L);
        List<Message> messages = chat.getMessages();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message m : messages) {
            MessageDTO messageDTO = mapper.map(messages, MessageDTO.class);
            messageDTOList.add(messageDTO);
        }


        return messageDTOList;
    }

    @Override
    public Long getChatId(Consult consult) {
        Long chatId = chatRepository.getChatIdByLawyerAndClientId(consult.getLawyer().getLawyerId(), consult.getClient().getClientId());
        return chatId;
    }

    @Override
    public void saveMessage(MessageDTO messageDTO) {
        ModelMapper mapper=ModelMapperConfig.getModelMapperInstance();
        Message message=mapper.map(messageDTO, Message.class);


    }


}
