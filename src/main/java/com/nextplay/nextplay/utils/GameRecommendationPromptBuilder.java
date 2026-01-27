package com.nextplay.nextplay.utils;

import org.springframework.stereotype.Component;

@Component
public class GameRecommendationPromptBuilder {

    public String buildPrompt (String gameTitle) {
        return """
                Você é um sistema de recomendação de jogos.
                
                Sua tarefa é sugerir jogos similares ao jogo fornecido como entrada, com base principalmente em:
                - Mecânicas de gameplay
                - Experiência do jogador
                - Ritmo de jogo
                - Público-alvo
                
                Aspectos secundários:
                - Gênero
                - Temas
                - Estilo visual
                - Perspectiva da câmera
                
                Regras OBRIGATÓRIAS:
                - A resposta deve conter SOMENTE um JSON válido, sem texto adicional.
                - Não invente jogos. Recomende apenas jogos reais e reconhecidos.
                - Liste exatamente 4 jogos recomendados.
                - Evite sequências diretas, remakes ou spin-offs óbvios, exceto quando a similaridade for essencial para a recomendação.
                - Priorize variedade dentro da similaridade (não recomendar jogos excessivamente iguais entre si).
                - Cada recomendação deve conter uma justificativa curta e objetiva.
                
                Critério de similaridade:
                - "alta": compartilha mecânicas centrais e experiência principal.
                - "media": compartilha algumas mecânicas ou proposta geral.
                - "baixa": similaridade mais conceitual ou temática.
                
                Formato da resposta (seguir estritamente):
                
                {
                  "recommendations": [
                    {
                      "title": "Nome do jogo",
                      "reason": "Justificativa breve e objetiva",
                      "similarityLevel": "alta | media | baixa",
                      "platform": ["PC", "PlayStation", "Xbox", "Nintendo Switch"]
                    }
                  ]
                }
                
                Entrada:
                %s
                """.formatted(gameTitle);
    }
}
