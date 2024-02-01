package org.example.config;

import org.example.entity.Line;
import org.example.entity.LineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class LinesWriter implements ItemWriter<Line>, StepExecutionListener {

    @Autowired
    LineRepository lineRepository;

    private final Logger logger = LoggerFactory
            .getLogger(LinesWriter.class);


    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Writer initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Writer ended.");
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(Chunk<? extends Line> lines) throws Exception {
        for (Line line : lines) {
            lineRepository.save(line);
            logger.debug("Wrote line " + line.toString());
        }
    }
}
