package dio.Projeto_API_REST.controller.dto;

import java.math.BigDecimal;

import dio.Projeto_API_REST.model.Card;

public record CardDto(Long card_id, String number, BigDecimal limit) {

    public CardDto(Card model) {
        this(model.getCard_Id(), model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setCard_Id(this.card_id);
        model.setNumber(this.number);
        model.setLimit(this.limit);
        return model;
    }
}
