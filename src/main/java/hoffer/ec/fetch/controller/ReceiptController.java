package hoffer.ec.fetch.controller;

import hoffer.ec.fetch.model.GetPointsResponse;
import hoffer.ec.fetch.model.ProcessResponse;
import hoffer.ec.fetch.model.Receipt;
import hoffer.ec.fetch.service.ReceiptService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Defines API Routing for the WebApp.
 */
@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    @Autowired
    final private ReceiptService receiptService;

    @PostMapping("/process")
    public ProcessResponse process(
            @NonNull @RequestBody Receipt receipt
    ) {
        final String id = receiptService.process(receipt);
        return new ProcessResponse(id);
    }

    @GetMapping("/{id}/points")
    public GetPointsResponse getPoints(
            @NonNull @PathVariable("id") String id
    ) {
        final long points = receiptService.getPoints(id);
        return new GetPointsResponse(points);
    }
}
