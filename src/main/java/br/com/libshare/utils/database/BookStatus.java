package br.com.libshare.utils.database;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum BookStatus {
    ALUGADO('A'), DISPONIVEL('D'), EMPRESTADO('E'), VENDIDO('V');

    private char bookStatus;

    public void setBookStatus(char bookStatus) {
		this.bookStatus = bookStatus;
	}

	BookStatus(char bookStatus) {
        this.bookStatus = bookStatus;
    }

    public char getBookStatus() {
        return bookStatus;
    }

    @Converter(autoApply = true)
    public static class Mapeador implements AttributeConverter<BookStatus, String> {

        @Override
        public String convertToDatabaseColumn(BookStatus x) {
            return String.valueOf(x.getBookStatus());
        }

        @Override
        public BookStatus convertToEntityAttribute(String y) {
            if (y == null) return null;
            if ("A".equals(y)) return ALUGADO;
            if ("D".equals(y)) return DISPONIVEL;
            if ("E".equals(y)) return EMPRESTADO;
            if ("V".equals(y)) return VENDIDO;
            throw new IllegalStateException("Valor inválido: " + y);
        }
    }
}